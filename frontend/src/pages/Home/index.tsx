import { useEffect, useState } from "react";
import { fetchProductsService } from '../../services/fetchProductsService';
import { PageTitle } from '../../components/PageTitle/index';
import { ProductCard } from '../../components/ProductCard';
import { Loading } from '../../components/Loding';
import { ErrorMessage } from '../../components/ErrorMessage';
import { BannerCarousel } from '../../components/BannerCarousel';
import styles from "./style.module.css";

export const Home = () => {
    const { products, loading, error } = fetchProductsService();
    const [fetchedProducts, setFetchedProducts] = useState<any[]>([]);


    console.log('setFetched',products);
    useEffect(() => {
        if (products) {
            setFetchedProducts(products);

        }
    }, [products]);

    if (loading) {
        return (
            <main>
                <Loading />
            </main>
        );
    }

    if (error) {
        return (
            <main>
                <ErrorMessage className="text-center" message={error} />
            </main>
        );
    }

    return (
        <main className={`${styles.main} container-padding`}>
            <section className="max-width">
                <BannerCarousel />
                <ProductCard productName={"Earring"}/>

                {error && <ErrorMessage className="text-center" message={error} />}
                {fetchedProducts.length > 0 ? (
                    <>
                        {fetchedProducts.map((item, index) => (
                            <section key={index} className={styles.productsContainer}>
                                <PageTitle title={item.categoryName} />
                                <ul>
                                    {item.products && item.products.map((product: any) => (
                                        <li key={product.productId}>
                                            <ProductCard
                                                productId={product.productId}
                                                productName={product.productName}
                                                productImageUrl={product.productImageUrl}
                                                productPrice={product.productPrice}
                                            />
                                        </li>
                                    ))}
                                </ul>
                            </section>
                        ))}
                    </>
                ) : (
                    <ErrorMessage
                        className="text-center"
                        message="There are no products available now, please, come back later."
                    />
                )}
            </section>
        </main>
    );
};
