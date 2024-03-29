package com.example.house_rental_app.models

class Profile{
    var name:String=""
    var housenumber:String=""
    var contact:String=""
    var id:String=""

    constructor(name:String,housenumber:String,contact:String,id:String){
        this.name=name
        this.housenumber=housenumber
        this.contact=contact
        this.id=id
    }
    constructor(){

    }
}
