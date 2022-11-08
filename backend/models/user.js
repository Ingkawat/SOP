const mongoose = require('mongoose')
const Schema = mongoose.Schema
const userSchema = new Schema({
    name : String,
    user_name: String,
    password : String,
    email : String,
    phonenumber : String
})

const UserModel = mongoose.model('user', userSchema, 'user')

module.exports = UserModel