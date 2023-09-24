const express = require('express');
const app = express();
const productRouter = require('./routes/productRoute');
const morgan = require('morgan');
const bodyParser = require('body-parser');

// using morgan logging
app.use(morgan('dev'));

// body parser
app.use(express.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.get('/', (req, res) => {
    res.redirect('/api/products');
});

app.use('/api/products', productRouter);

app.use((req,res) => {
    res.json({
        msg:"404"
    })
});

const port = 5000;
app.listen(port, ()=> console.log(`Server is Listening to Requests at Port : ${port}`));