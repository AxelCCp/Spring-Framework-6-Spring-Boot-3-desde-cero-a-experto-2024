import { PropTypes } from "prop-types"
import { ProductDetail } from "./ProductDetail"

export const ProductGrid = ({products}) => {
    return(
        <>
             <table>
                <thead>
                    <tr>
                        <th>name</th>
                        <th>description</th>
                        <th>price</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(p => {
                        return <ProductDetail p={p} key={p.name}/>
                    })}
                </tbody>
            </table>
        </>
    )
}

ProductGrid.propTypes = {
    products : PropTypes.array.isRequired
}