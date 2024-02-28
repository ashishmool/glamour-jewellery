import { useEffect, useState } from 'react';
import { useNavigate, useParams } from "react-router-dom";
import { Loading } from "../../components/Loading";
import { ProductDetailsActions } from "../../components/ProductDetailsActions";
import { fetchProductDetailsService } from "../../services/fetchProductDetailsService";
import { moneyFormatter } from "../../utils/moneyFormatter";
import { AddCommentModal } from '../../components/AddCommentModal';
import { FormButton } from '../../components/FormButton/index';
import { CommentCard } from '../../components/CommentCard';
import { ToastContainer } from 'react-toastify';
import { useAuthContext } from '../../hooks/useAuthContext';
import styles from "./style.module.css";


export const ProductDetails = () => {

  const { token } = useAuthContext();
  const { productId } = useParams();
  const navigate = useNavigate();

  const {
    fetchProductDetails,
    productDetails,
    favoriteProducts,
    productsOnCart,
    loading,
    error } = fetchProductDetailsService();

  const [showCommentModal, setShowCommentModal] = useState<boolean>(false);


  function handleAddComment() {

    if (!token) { navigate("/login"); return };

    setShowCommentModal(true);

  }


  useEffect(() => {

    fetchProductDetails(productId);

  }, []);


  if (loading) {
    return (
      <main className="container-padding">
        <Loading />
      </main>
    )
  }


  return (
    <main className="container-padding">
      {showCommentModal && <AddCommentModal showModalState={setShowCommentModal} productId={productId} />}
      <ToastContainer hideProgressBar={true} />
      <section className={`${styles.container} max-width`}>
        <ProductDetailsActions
          productID={productId}
          favoriteProducts={favoriteProducts}
          productsOnCart={productsOnCart}
        />
        <section className={styles.flexContainer}>
          <div className={styles.productDetailsContainer}>
            <h3>{productDetails?.data.productName}</h3>
            <p className={styles.price}>Rs.
              {(productDetails?.data.productPrice)}/-
            </p>
            <p>{productDetails?.data.productDescription}</p>
          </div>
          <div className={styles.productImageContainer}>
            {productDetails?.data?.image ? (
                <img src={'data:image/png;base64,' + productDetails?.data?.image} alt={productDetails?.data.productName} />
            ) : (
                <p>No image available</p>
            )}


          </div>
        </section>
        <section className={styles.commentsSection}>
          <h2>Any Comments?</h2>
          <div className={styles.commentsContainer}>
            {productDetails?.data.posts?.length == 0 && (
              <p className={styles.noReviewsMessage}>This product has no reviews, be the first one!</p>
            )}
            {productDetails?.data.posts?.map((comment, index) => (
              <CommentCard key={index} title={comment.title} content={comment.content} />
            ))}
          </div>
          <FormButton onClick={handleAddComment}>Add a comment</FormButton>
        </section>
      </section>
    </main>
  );
}
