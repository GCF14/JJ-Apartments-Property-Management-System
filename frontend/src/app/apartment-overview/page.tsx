"use client";
import React, { useState } from 'react';
import { Plus, Edit, Trash2 } from 'lucide-react';
import { ApartmentForm } from '@/components/apartmentForm';
import { ApartmentList } from '@/components/apartmentList';








const MainContent = () => {
  return (
    <div className="flex-1 flex flex-col min-h-screen">
      <header className="bg-white shadow-sm border-b p-2 ">
        <div className="flex items-center gap-4">
          <h1 className="text-2xl font-bold text-gray-900">Apartment Overview</h1>
        </div>
      </header>

      
      <div className="flex flex-1">
        <ApartmentForm />
        <ApartmentList />
      </div>
    </div>
  );
};

const App = () => {
  return (
    <div className="h-screen bg-gray-100">
      <MainContent />
    </div>
  );
};

export default App;