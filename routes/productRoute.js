const express = require('express');
const router = express.Router();
const dbOperations = require('../database/db');

// get all products
router.get('/', (req, res) => {
    const rows = dbOperations.getAllProducts;
    console.log(rows);
    res.status(200).json(rows);
});


router.get('/:id', (req, res) => {
    res.json({
        msg: '/products/:id'
    })
});

router.post('/', (req, res) => {
    res.json({
        msg: 'post /products'
    });
    res.status(404);
});

router.put('/:id', (req, res) => {
    res.json({
        msg:'put /products/:id'
    })
});

router.delete('/:id', (req, res) => {
    res.json({
        msg:'delete /products/:id'
    })
});

module.exports = router ;