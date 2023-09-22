const express = require('express');
const app = express();
const productRouter = require('./routes/productRoute');
const morgan = require('morgan');

// using morgan logging
app.use(morgan('dev'));

app.get('/', (req, res) => {
    res.redirect('/products');
});

app.use('/products', productRouter);

app.use((req,res) => {
    res.json({
        msg:"404"
    })
});

const port = 5000;
app.listen(port, ()=> console.log(`Server is Listening to Requests at Port : ${port}`));