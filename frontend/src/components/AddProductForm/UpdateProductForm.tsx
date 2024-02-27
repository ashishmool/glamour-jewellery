import React, { FormEvent, useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { Label } from "../Label";
import { LoadingScreen } from "../LoadingScreen";
import { ErrorMessage } from "../ErrorMessage";
import { FormButton } from "../FormButton";
import { useAuthContext } from "../../hooks/useAuthContext";
import { api } from "../../services/api";
import styles from "../../pages/UpdateProduct/style.module.css";

export const UpdateProductForm = () => {
    const { token } = useAuthContext();
    const { id } = useParams(); // Get the ID from the URL params
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [product, setProduct] = useState({
        productName: "",
        productPrice: "",
        productCategory: "",
        productImageUrl: "",
        productDescription: "",
        stockQuantity: 0,
    });

    useEffect(() => {
        if (id) {
            // If ID is provided, fetch product data
            fetchProductData(id);
        }
    }, [id]);

    const fetchProductData = async (productId) => {
        try {
            setLoading(true);
            const response = await api.get(`product/getById/${productId}`);
            const productData = response.data;
            setProduct(productData);
            setLoading(false);
        } catch (error) {
            console.error("Error fetching product:", error);
            setLoading(false);
            setError("Failed to fetch product data. Please try again later.");
        }
    };

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            setLoading(true);
            await api.put(`product/update/${id}`, product, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setLoading(false);
            // Redirect or show success message
        } catch (error) {
            console.error("Error updating product:", error);
            setLoading(false);
            setError("Failed to update product. Please try again later.");
        }
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setProduct(prevState => ({
            ...prevState,
            [name]: value,
        }));
    };

    return (
        <form onSubmit={handleSubmit}>
            {loading && <LoadingScreen />}
            <Label label="Product name">{product.productName}</Label>
            <Label label="Price">{product.productPrice}</Label>
            <Label label="Category">{product.productCategory}</Label>
            <Label label="Product image">
                <img
                    src={product.productImageUrl}
                    alt="Product"
                    style={{ maxWidth: "100px", maxHeight: "100px" }}
                />
            </Label>
            <Label label="Description">{product.productDescription}</Label>
            <Label label="Stock Quantity">{product.stockQuantity}</Label>
            <div className="form-actions">
                {error && <ErrorMessage message={error} />}
                <Link to="/">Go home</Link>
                <FormButton>Update product</FormButton>
            </div>
        </form>
    );
};
