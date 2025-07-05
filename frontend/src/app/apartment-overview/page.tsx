'use client';

import React, { useState } from 'react';
import Link from 'next/link';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent } from '@/components/ui/card';
import { Printer } from 'lucide-react';

const navItems = [
  { name: 'Dashboard', href: '/dashboard' },
  { name: 'Apartments', href: '/apartments' },
  { name: 'Tenants', href: '/tenants' },
  { name: 'Financial', href: '/financial' },
  { name: 'Communication', href: '/communication' },
  { name: 'Move Out', href: '/move-out' },
  { name: 'Log Out', href: '/logout' },
];

const records = [
  { id: 1, date: 'May 09, 2021', tenant: 'Aranzado, Melanie Marfil', house: '110', invoice: '1', amount: 5000 },
  { id: 2, date: 'May 09, 2021', tenant: 'Aranzado, Melanie Marfil', house: '110', invoice: '1', amount: 7000 },
  { id: 3, date: 'May 10, 2021', tenant: 'Dela Rosa, Mary Jane Amores', house: '551', invoice: '2', amount: 800 },
  { id: 4, date: 'May 10, 2021', tenant: 'Dela Rosa, Mary Jane Amores', house: '551', invoice: '2', amount: 8000 },
];

const MainContent = () => {
  const [month, setMonth] = useState('2021-05');
  // In a real app, filter records by month
  const filteredRecords = records;
  const totalAmount = filteredRecords.reduce((sum, r) => sum + r.amount, 0);

  return (
    <div className="flex-1 flex flex-col min-h-screen">
      <header className="bg-white shadow-sm border-b p-4">
        <h1 className="text-2xl font-bold text-gray-900">Financial Tracking</h1>
      </header>
      <div className="p-4">
        <Card>
          <CardContent>
            <div className="flex items-center gap-4 mb-4">
              <Label htmlFor="month">Month of:</Label>
              <Input
                id="month"
                type="month"
                value={month}
                onChange={e => setMonth(e.target.value)}
              />
              <Button onClick={() => {/* apply filter logic */}}>Filter</Button>
              <Button
                variant="outline"
                onClick={() => window.print()}
                className="ml-auto"
              >
                <Printer className="mr-2 h-4 w-4" />
                Print
              </Button>
            </div>
            <div className="overflow-x-auto">
              <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                  <tr>
                    <th className="px-4 py-2 text-left text-sm font-medium text-gray-500">#</th>
                    <th className="px-4 py-2 text-left text-sm font-medium text-gray-500">Date</th>
                    <th className="px-4 py-2 text-left text-sm font-medium text-gray-500">Tenant</th>
                    <th className="px-4 py-2 text-left text-sm font-medium text-gray-500">House #</th>
                    <th className="px-4 py-2 text-left text-sm font-medium text-gray-500">Invoice</th>
                    <th className="px-4 py-2 text-right text-sm font-medium text-gray-500">Amount</th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  {filteredRecords.map(r => (
                    <tr key={r.id}>
                      <td className="px-4 py-2 text-sm text-gray-500">{r.id}</td>
                      <td className="px-4 py-2 text-sm text-gray-700">{r.date}</td>
                      <td className="px-4 py-2 text-sm text-gray-700">{r.tenant}</td>
                      <td className="px-4 py-2 text-sm text-gray-700">{r.house}</td>
                      <td className="px-4 py-2 text-sm text-gray-700">{r.invoice}</td>
                      <td className="px-4 py-2 text-sm text-gray-700 text-right">
                        {r.amount.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
                      </td>
                    </tr>
                  ))}
                </tbody>
                <tfoot className="bg-gray-50">
                  <tr>
                    <td colSpan={5} className="px-4 py-2 text-right font-semibold">Total Amount</td>
                    <td className="px-4 py-2 text-right font-semibold">
                      {totalAmount.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
                    </td>
                  </tr>
                </tfoot>
              </table>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default function FinancialTrackingPage() {
  return (
    <div className="h-screen bg-gray-100 flex">
      <aside className="w-64 bg-white border-r">
        <nav className="flex flex-col py-4 space-y-2">
          {navItems.map(item => (
            <Link key={item.name} href={item.href} passHref>
              <a className={`flex items-center px-4 py-2 text-sm font-medium rounded ${
                item.name === 'Financial'
                  ? 'bg-yellow-200 text-gray-900'
                  : 'text-gray-700 hover:bg-gray-100'
              }`}>
                {item.name}
              </a>
            </Link>
          ))}
        </nav>
      </aside>
      <MainContent />
    </div>
  );
}
