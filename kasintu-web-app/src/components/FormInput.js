import React from "react";

const FormInput = (props) => {

    const { className, label, errorMessage, onChange, id, ...inputProps } = props;

    return(
        <div className={className}>
            <label>{label}</label>
            <input {...inputProps} onChange={onChange}/>
            <label>{errorMessage}</label>
        </div>
    );
};

export default FormInput;