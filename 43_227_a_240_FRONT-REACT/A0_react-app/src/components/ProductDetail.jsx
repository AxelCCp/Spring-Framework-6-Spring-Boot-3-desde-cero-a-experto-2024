import { PropTypes } from "prop-types"

export const ProductDetail = ({p={}}) => {               // p={} : con esto se le dice a react q es un objeto. con esto se quita un warning q da a veces.

    return (  
        <tr>
            <td>{p.name}</td>
            <td>{p.description}</td>
            <td>{p.price}</td>
        </tr>
    );
}


ProductDetail.propTypes = {
    p : PropTypes.object.isRequired
}