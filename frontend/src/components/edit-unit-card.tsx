'use client'

import { useState, useEffect } from 'react'
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Unit } from './expenses-list'
import { ErrorModal } from './error-modal'

type Props = {
  open: boolean
  onClose: () => void
  onSave: (updated: any) => void
  unit: any // ideally Unit type
}

export default function EditUnitCard({ open, onClose, onSave, unit }: Props) {
    const [form, setForm] = useState<Unit>(() => ({ ...unit }));
    const [originalForm, setOriginalForm] = useState<Unit>(() => ({ ...unit }));
    const [error, setError] = useState<string | null>(null);
    const [errorModalOpen, setErrorModalOpen] = useState(false);
    const [errorMessage, setErrorMessage] = useState<string>("");

    useEffect(() => {
    if (open && unit) {
        setForm({ ...unit });
        setOriginalForm({ ...unit });
    }
    }, [open, unit]);

    const handleChange = (field: string, value: any) => {
        setForm(prev => ({ ...prev, [field]: value }))
    }

    const handleSubmit = () => {
        const validationError = validateForm();
        if (validationError) {
            setErrorMessage(validationError);
            setErrorModalOpen(true);
            return;
        }

        setErrorMessage("");
        setErrorModalOpen(false);
        onSave(form);
        onClose();
    };

    const handleCancel = () => {
        setForm({ ...originalForm });
    };

    const validateForm = () => {
        const missingFields: string[] = [];
        if (!form.name) missingFields.push("Apartment Name");
        if (!form.unitNumber) missingFields.push("Unit Number");
        if (!form.description) missingFields.push("Description");
        if (form.numOccupants == null) missingFields.push("Max Number of Occupants");
        if (form.price == null || form.price === "") missingFields.push("Price");

        if (missingFields.length > 0) {
            return `Please fill in the following required field${missingFields.length > 1 ? "s" : ""}: ${missingFields.join(", ")}.`;
        }

        if (typeof form.numOccupants !== "number" || isNaN(form.numOccupants) || form.numOccupants < 0) {
            return "Max Number of Occupants must be a non-negative number.";
        }

        if (typeof form.price !== "number" || isNaN(form.price) || form.price < 0) {
            return "Price must be a non-negative number.";
        }

        return null;
    };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-lg">
        <DialogHeader>
            <DialogTitle>Edit Unit Record</DialogTitle>
        </DialogHeader>
            <div className="grid gap-4 py-2">

                <div>
                    <Label className="py-1">Apartment Name <span className="text-red-500">*</span></Label>
                        <Input
                        type="text"
                        value={form.name ?? ""}
                        onChange={(e) => handleChange("name", e.target.value)}
                        />
                </div>
                <div>
                    <Label className="py-1">Unit Number <span className="text-red-500">*</span></Label>
                        <Input
                        type="text"
                        value={form.unitNumber ?? ""}
                        onChange={(e) => handleChange("unitNumber", e.target.value)}
                        />
                </div>
                <div>
                    <Label className="py-1">Description <span className="text-red-500">*</span></Label>
                        <Input
                        type="text"
                        value={form.description ?? ""}
                        onChange={(e) => handleChange("description", e.target.value)}
                        />
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div>
                        <Label className="py-1">Max Number of Occupants <span className="text-red-500">*</span></Label>
                        <Input
                            type="number"
                            value={form.numOccupants ?? ""}
                            onChange={(e) => {
                                const val = e.target.value;
                                handleChange("numOccupants", val === "" ? "" : Number(val));
                            }}
                            />
                    </div>
                    <div>
                        <Label className="py-1">Price <span className="text-red-500">*</span></Label>
                        <Input
                            type="number"
                            value={form.price ?? ""}
                            onChange={(e) => {
                                const val = e.target.value;
                                handleChange("price", val === "" ? "" : Number(val));
                            }}
                            />
                    </div>
                </div>
                
                {error && (
                    <div className="text-sm text-red-600 bg-red-100 rounded px-3 py-2 mb-2">
                        {error}
                    </div>
                )}
            <ErrorModal
                open={errorModalOpen}
                title="Unable to Save Unit"
                message={errorMessage}
                onClose={() => {
                    setErrorModalOpen(false);
                    setErrorMessage("");
                }}
            />
        </div>
        <DialogFooter>
          <Button variant="secondary"  
            onClick={() => {
                handleCancel();
                onClose();
            }}>
                Cancel
            </Button>
          <Button onClick={handleSubmit}>Save</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  )
}
