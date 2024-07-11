import { useEffect, useState } from "react"
import { listProduct } from "../services/ProductService";
import { ProductGrid } from "./ProductGrid";
import { PropTypes } from "prop-types"
import { ProductForm } from "./ProductForm";

export const ProductApp = ({title}) => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        const result = listProduct();
        setProducts(result);
    }, []);  //con [] el useEffect se ejecuta solo cuando se crea el componente


    const handlerAddProduct = (product) => {
        console.log(product);
        setProducts([...products, {...product}]);   //se mantienen los producto que ya estaban y se pasa product desestructurado.
    }

    return (
        <div>

            <h1>{title}</h1>

            <div>

                <div>
                    <ProductForm handlerAdd = {handlerAddProduct}/>
                </div>

                <div>
                    <ProductGrid products={products}/>
                </div>

            </div>
           
        </div>
    )
}

ProductGrid.propTypes = {
    p : PropTypes.string.isRequired
}