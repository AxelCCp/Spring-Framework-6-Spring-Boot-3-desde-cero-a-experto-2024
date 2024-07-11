import { useState } from "react";

//234
export const ProductForm = ({handlerAdd}) => {
    
    const initialDataForm = {
        name: '',
        description: '',
        price: ''
    }

    const[form, setForm] = useState(initialDataForm);

    const{name, description, price} = form;

    return (
        <form onSubmit={(event) => {

            event.preventDefault();                         //para evitar el refresh automatico de la pagina al apretar el boton del formulario.

            if(!name || !description || !price) {
                alert('debe completar los datos del formulario!');
                return;
            }
            //console.log(form);
            handlerAdd(form);
            setForm(initialDataForm);   
        }}>

            <div>
                <input placeholder="Name" 
                        style={{marginBottom: '6px'}}
                        name="name" 
                        value= {name}
                        onChange={(event) => setForm({
                            ...form, 
                            name: event.target.value
                        })}
                />
            </div>
            
            <div>
                <input placeholder="Description" 
                        style={{marginBottom: '6px'}}
                        name="description" 
                        value={description} 
                        onChange={(event) => setForm({...form, description: event.target.value})}/>
            </div>
           
            <div>
                <input placeholder="Price"
                        style={{marginBottom: '6px'}} 
                        name="price" 
                        value={price} 
                        onChange={(event) => setForm({...form, price: event.target.value})}/>
            </div>
            
            <div>
                <button type="submit">Create</button>
            </div>
            

        </form>


    );
}