const express = require('express');
const app = express();
const morgan = require('morgan');

// using morgan logging
app.use(morgan('dev'));


app.get('/products', (req, res) => {
    res.json({
        msg:'/products'
    })
});

app.get('/', (req, res) => {
    res.redirect('/products');
});

app.get('/products/:id', (req, res) => {
    res.json({
        msg: '/products/:id'
    })
});

app.post('/products', (req, res) => {
    res.json({
        msg: 'post /products'
    });
    res.status(404);
});

app.put('/products/:id', (req, res) => {
    res.json({
        msg:'put /products/:id'
    })
});

app.delete('/products/:id', (req, res) => {
    res.json({
        msg:'delete /products/:id'
    })
});

app.use((req,res) => {
    res.json({
        msg:"404"
    })
});

const port = 5000;
app.listen(port, ()=> console.log(`Server is Listening to Requests at Port : ${port}`));