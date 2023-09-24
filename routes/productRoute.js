const express = require('express');
const router = express.Router();
const controller = require('../controllers/productController');
// const dbOperations = require('../database/db');

// get all products
router.get('/', controller.getAllProducts );


// get single product
router.get('/:id', controller.getSingleProduct );

// post a new product
router.post('/', controller.saveProduct );

// update a product
router.put('/:id', controller.updateProduct );

// delete a product
router.delete('/:id', controller.deleteProduct );

module.exports = router ;