export type Tenant = {
  id: number;
  lastName: string;
  middleName: string;
  firstName: string;
  unit: string;
  building: string;
  moveIn: string; // can be changed to date
  moveOut: string; // can be changed to date
};

type Props = {
  tenants: Tenant[];
};

const TenantTable: React.FC<Props> = ({ tenants }) => {
  return (

    // scroll
    <div className="border rounded-lg overflow-y-auto max-h-[500px] shadow-sm"> 
      <table className="min-w-full border-collapse text-sm text-center"> 
        <thead className="bg-gray-200 sticky top-0 z-10"> 
          <tr>
            <th className="border border-gray-300 p-2">NO.</th> 
            <th className="border border-gray-300 p-2">LAST NAME</th>
            <th className="border border-gray-300 p-2">MIDDLE NAME</th>
            <th className="border border-gray-300 p-2">FIRST NAME</th>
            <th className="border border-gray-300 p-2">UNIT</th>
            <th className="border border-gray-300 p-2">BUILDING</th>
            <th className="border border-gray-300 p-2">MOVE-IN DATE</th> 
            <th className="border border-gray-300 p-2">MOVE-OUT DATE</th> 
            <th className="border border-gray-300 p-2">INFORMATION</th> 
            <th className="border border-gray-300 p-2">ACTION</th> 
          </tr>
        </thead>
        <tbody>
          {tenants.length === 0 ? (
            // displays text when no tenants are found
            <tr>
              <td colSpan={10} className="border border-gray-300 p-4 text-gray-500">No tenants found.</td>
            </tr>
          ) : (
            tenants.map((tenant, index) => (
              <tr key={tenant.id} className="bg-white hover:bg-gray-50">
                <td className="border border-gray-300 p-2">{index + 1}</td>
                <td className="border border-gray-300 p-2">{tenant.lastName}</td>
                <td className="border border-gray-300 p-2">{tenant.middleName}</td>
                <td className="border border-gray-300 p-2">{tenant.firstName}</td>
                <td className="border border-gray-300 p-2">{tenant.unit}</td>
                <td className="border border-gray-300 p-2">{tenant.building}</td>
                <td className="border border-gray-300 p-2">{tenant.moveIn}</td>
                <td className="border border-gray-300 p-2">{tenant.moveOut}</td>
                <td className="border border-gray-300 p-2">
                  <button className="bg-blue-500 text-white text-xs px-2 py-1 rounded hover:bg-blue-600">View</button>
                </td>
                <td className="border border-gray-300 p-2 space-x-1">
                  <button className="bg-green-500 text-white text-xs px-2 py-1 rounded hover:bg-green-600">Edit</button>
                  <button className="bg-red-500 text-white text-xs px-2 py-1 rounded hover:bg-red-600">Delete</button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default TenantTable;