import { useState, useEffect } from "react";
import {
  Card,
  CardHeader,
  CardTitle,
  CardContent,
  CardFooter,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button"
import { Unit } from "@/components/expenses-list"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"
import { Input } from "@/components/ui/input"
import {DatePicker} from "@/components/date-picker"
import { Payment } from "./payments-list";
import { ErrorModal } from "@/components/error-modal";
interface Props {
  setPayment:  React.Dispatch<React.SetStateAction<Payment[]>>;
}

export default function AddPaymentButton({setPayment}: Props) {
    const [isOpen, setIsOpen] = useState(false);
    const [unitId, setUnitId] = useState<number>(0);
    const [modeOfPayment, setModeOfPayment] = useState("");
    const [amount, setAmount] = useState<string>("");
    const [dueDate, setDueDate] = useState<Date | undefined>(undefined);
    const [monthOfStart, setMonthOfStart] = useState<Date | undefined>(undefined);
    const [monthOfEnd, setMonthOfEnd] = useState<Date | undefined>(undefined);
    const [units, setUnits] = useState<Unit[]>([]);
    const [localError, setLocalError] = useState<string | null>(null);
    const modeOptions = ["Cash", "GCash", "Bank Transfer", "Online Payment", "Other"];
    useEffect(() => {
        const fetchUnits = async () => {
            try {
                const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/units`);
                const data = await res.json();
            setUnits(data);
            } catch (error) {
            console.error("Error fetching units:", error);
            }
        };
        fetchUnits();
    }, []);
    const handleSubmit = async(e: React.MouseEvent ) => {
        e.preventDefault();
        e.stopPropagation();
        if (
          !unitId ||
          !modeOfPayment ||
          !amount ||
          !dueDate ||
          !monthOfStart ||
          !monthOfEnd
        ) {
          setLocalError("Please fill out all required fields");
          return;
        }

        const body = {
            unitId,
            modeOfPayment,
            amount: Number(amount),
            dueDate: dueDate?.toLocaleDateString("en-CA"),
            monthOfStart: monthOfStart?.toLocaleDateString("en-CA"),
            monthOfEnd: monthOfEnd?.toLocaleDateString("en-CA")
        }

        try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/payments/add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      });
      
      if (!res.ok) {
        throw new Error("Failed to create payment record");
      }
      setLocalError(null);

      setIsOpen(false);
      const saved = await res.json();
      setPayment(prev => [saved, ...prev]);


    } catch (error) {
      console.error("Error submitting payment:", error);
    }
    }

    return (
    <>
        <ErrorModal
            open={!!localError}
            message={localError || ""}
            onClose={() => setLocalError(null)}
          />
        <Button onClick={() => {setIsOpen(true);
                                setUnitId(0);
                                setModeOfPayment("");
                                setAmount("");
                                setDueDate(undefined);
                                setMonthOfStart(undefined);
                                setMonthOfEnd(undefined);
                                setLocalError(null);}}>
          
            Add
        </Button>

        {isOpen && (
        <div className="fixed inset-0 z-40 flex items-center justify-center bg-black/50 backdrop-blur-sm">
          <Card className="w-[600px] bg-card">
            <CardHeader>
              <CardTitle>Add Payment Record</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="py-1 text-sm text-gray-900">
                    Unit
                    <Select onValueChange={(value) => setUnitId(Number(value))}>
                        <SelectTrigger className="w-full h-11 rounded-md border px-3 text-left">
                            <SelectValue placeholder="Select Unit" />
                        </SelectTrigger>
                        <SelectContent className="w-full">
                            {units.map((u) => (
                            <SelectItem key={u.id} value={String(u.id)}>
                                Unit {u.unitNumber} - {u.name}
                            </SelectItem>
                            ))}
                        </SelectContent>
                    </Select>
                </div>

                <div className="py-1 text-sm text-gray-900">
                    Mode Of Payment
                    <Select onValueChange={(value) => setModeOfPayment(value)}>
                        <SelectTrigger className="w-full h-11 rounded-md border px-3 text-left">
                            <SelectValue placeholder="Select Mode of Payment" />
                        </SelectTrigger>
                        <SelectContent className="w-full">
                            {modeOptions.map((mode) => (
                                <SelectItem key={mode} value={mode}>
                                    {mode}
                                </SelectItem>
                            ))}
                        </SelectContent>
                    </Select>
                </div>

                
                
               
                <div className="py-1 text-sm text-gray-900">
                    Amount
                    <Input
                        type="number"
                        placeholder="Amount"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                    />
                </div>

                <div className="py-1 text-sm text-gray-900">
                    Due Date
                    <DatePicker date={dueDate} setDate={setDueDate}/>
                </div>
                            
                <div className="grid gap-4 py-1">
                    <div className="grid grid-cols-2 gap-4">
                    <div className="py-1 text-sm text-gray-900">
                            Month Of Start
                            <DatePicker date={monthOfStart} setDate={setMonthOfStart}/>
                        </div>

                        <div className="py-1 text-sm text-gray-900">
                            Month Of End
                            <DatePicker date={monthOfEnd} setDate={setMonthOfEnd}/>
                        </div>
                    </div>
                </div>
              
            </CardContent>

            <CardFooter className="flex justify-between">
              <div className="flex gap-4">
                <Button variant="secondary"
                  onClick={() => setIsOpen(false)}
                >
                  Cancel
                </Button>
                <Button
                  onClick={handleSubmit}
                >
                  Submit
                </Button>
              </div>
            </CardFooter>
          </Card>
        </div>
      )}
    </>
  );
}