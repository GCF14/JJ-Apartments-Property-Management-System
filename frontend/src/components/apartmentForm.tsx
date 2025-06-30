import { Plus } from "lucide-react";
import { useState } from "react";

export function ApartmentForm() {
  
    const [formData, setFormData] = useState({
        apartment: 'Dela Cruz Apartment',
        apartmentNo: '',
        description: '',
        price: ''
    });

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Form submitted:', formData);
  };

  return (
    <div className="w-80  bg-white border-r border-gray-200 p-6 overflow-y-auto">
      <div className="mb-6">
        <h2 className="text-xl font-bold text-gray-900 mb-2">Apartment Form</h2>
        <p className="text-sm text-gray-600">Add or edit apartment details</p>
      </div>

      <div className="space-y-6">   
        
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Select Apartment
          </label>
          <select 
            name="apartment"
            value={formData.apartment}
            onChange={handleInputChange}
            className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 appearance-none"
          >
            <option value="Dela Cruz Apartment">Dela Cruz Apartment</option>
            <option value="Santos Apartment">Santos Apartment</option>
            <option value="Garcia Apartment">Garcia Apartment</option>
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Apartment No.
          </label>
          <input 
            type="text"
            name="apartmentNo"
            value={formData.apartmentNo}
            onChange={handleInputChange}
            placeholder="e.g. #110"
            className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Description
          </label>
          <textarea 
            name="description"
            value={formData.description}
            onChange={handleInputChange}
            rows="1"
            placeholder="e.g. 2 bedroom and 1 rest room"
            className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 resize-none"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Price
          </label>
          <div className="relative">
            <span className="absolute left-3 top-3 text-gray-500">₱</span>
            <input 
              type="number"
              name="price"
              value={formData.price}
              onChange={handleInputChange}
              placeholder="0.00"
              className="w-full pl-8 pr-3 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>
        </div>

        <div className="flex gap-3 pt-6">
          <button 
            onClick={handleSubmit}
            className="flex-1 bg-blue-600 text-white py-3 px-4 rounded-lg hover:bg-blue-700 transition-colors font-medium"
          >
            Save
          </button>
          <button 
            type="button"
            className="flex-1 bg-gray-300 text-gray-700 py-3 px-4 rounded-lg hover:bg-gray-400 transition-colors font-medium"
          >
            Cancel
          </button>
        </div>

        <div className="pt-4 border-t border-gray-200">
          <button 
            type="button"
            className="flex items-center justify-center gap-2 w-full py-2 px-4 text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
          >
            <Plus size={16} />
            Add New Apartment
          </button>
        </div>
      </div>
    </div>
  );

};