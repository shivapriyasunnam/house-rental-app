package com.example.house_rental_app.models

class Booking{
    var name:String=""
    var sizeofthehouse:String=""
    var locationofthehouse:String=""
    var id:String=""
    constructor(name:String,sizeofthehouse:String,locationofthehouse:String,id:String){
        this.name=name
        this.sizeofthehouse=sizeofthehouse
        this.locationofthehouse=locationofthehouse
        this.id=id
    }
    constructor(){

    }
}
