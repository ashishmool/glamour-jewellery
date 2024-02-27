import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { api } from '../../services/api';

export const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await api.get('product/getAll');
            setProducts(response.data);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    const handleDelete = async (productId) => {
        try {
            await api.delete(`product/delete/${productId}`);
            fetchProducts(); // Refresh product list after deletion
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };

    return (
        <div>
            <h2>Product List</h2>
            <table>
                <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {products.map((product) => (
                    <tr key={product.productId}>
                        <td>{product.productName}</td>
                        <td>{product.productPrice}</td>
                        <td>{product.productCategory}</td>
                        <td>{product.productDescription}</td>
                        <td>
                            <Link to={`/edit/${product.productId}`}>
                                <button>Edit</button>
                            </Link>
                            <button onClick={() => handleDelete(product.productId)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};
