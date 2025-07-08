"use client";
import React, { useState } from "react";
import { TenantPopUp } from "@/components/TenantPopUp";
import { TenantMgt } from "@/components/TenantMgt";
import TenantTable, { Tenant } from "@/components/ui/TenantTable";

export default function TenantsManagementPage() {
    type Unit = {
        id: number;
        unitNumber: string;
        occupied: boolean;
    };

    const [modalOpen, setModalOpen] = useState(false);

    // THIS IS A PLACEHOLDER DATA ----------------
    const [tenants, setTenants] = useState<Tenant[]>([
        {
            id: 1,
            firstName: "JOHN",
            middleName: "MARK",
            lastName: "DOE",
            unit: "101",
            building: "DELA CRUZ APARTMENT",
            moveIn: "JULY 07, 2025",
            moveOut: "AUGUST 06, 2025",
        }
    ]); 
    // -------------------------------------------

    // filter for the search functionality
    const [search, setSearch] = useState('');
    const filteredTenants = tenants.filter(tenant => {
        const lowerCaseSearch = search.toLowerCase();
        return (
            (tenant.firstName?.toLowerCase() || '').includes(lowerCaseSearch) ||
            (tenant.lastName?.toLowerCase() || '').includes(lowerCaseSearch) ||
            (tenant.middleName?.toLowerCase() || '').includes(lowerCaseSearch) ||
            (tenant.unit?.toLowerCase() || '').includes(lowerCaseSearch) ||
            (tenant.building?.toLowerCase() || '').includes(lowerCaseSearch) ||
            (tenant.moveIn?.toLowerCase() || '').includes(lowerCaseSearch) ||
            (tenant.moveOut?.toLowerCase() || '').includes(lowerCaseSearch)
        );
    });

    const toggleModal = () => setModalOpen(!modalOpen);

    const handleAddTenant = (tenantData: Omit<Tenant, 'id'>) => {
        const newId = tenants.length > 0 ? Math.max(...tenants.map(t => t.id)) + 1 : 1;
        const newTenant: Tenant = { ...tenantData, id: newId };
        setTenants(prevTenants => [...prevTenants, newTenant]);
        console.log('New tenant added:', newTenant);
    };

    return (
        <div className="p-6">
            <header className="flex items-center justify-between mb-6">
                <h1 className="text-3xl font-bold text-gray-900">TENANT MANAGEMENT</h1>
                <button
                    onClick={() => setModalOpen(true)}
                    className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors"
                >
                    Add Tenant
                </button>
            </header>
            
            <div className="flex items-center mb-6 space-x-2">
                <div className="flex-grow relative">
                    <input
                        type="text"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        placeholder="SEARCH"
                        className="w-full p-2 border border-gray-300 rounded-lg pl-10 focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                    <span className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">🔍</span>
                </div>
                <button className="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors">
                    FILTER
                </button>
            </div>

            <TenantTable tenants={filteredTenants} /> 

            <div className="mt-4 text-gray-600 text-sm">
                SHOWING {filteredTenants.length > 0 ? 1 : 0} OF {filteredTenants.length} ENTRIES
            </div>

            <TenantPopUp modalOpen={modalOpen}>
                <TenantMgt toggleModal={toggleModal} onSubmit={handleAddTenant} />
            </TenantPopUp>
        </div>
    );
}