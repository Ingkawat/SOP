const express = require('express')
const app = express()
var cors = require('cors')
const path = require("path")
const PORT = process.env.PORT || 3000
require('./mongoose')
app.use(cors({
  origin:true,
  credentials:true
}))


app.use(express.json())


const userRouter = require('./routes/user')
app.use(userRouter.router)



app.listen(PORT, () => {
  console.log(`Application is running on port ${PORT}`)
})

module.exports = app