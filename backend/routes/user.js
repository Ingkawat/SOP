const express = require("express");
const User = require('../models/user')
router = express.Router();

router.post("/login", async (req, res) => {
    const username  = req.body.user_name
    const password  = req.body.password
    const user = await User.find({user_name : username})

    if(user[0] != undefined){
        if (user[0].password == password){
            res.json(user[0])
        }else{
            res.json("incorrect password ")
        }
    }else{
        res.json("incorrect username")
    }
    
  })

exports.router = router;




