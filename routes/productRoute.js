const express = require('express');
const router = express.Router();
// const dbOperations = require('../database/db');
const Pool = require('pg').Pool;


// create a database connection
const pool = new Pool({
        user: 'asdf',
        host: 'localhost',
        database: 'testPG',
        password: '',
        port: 5432,
      });




// get all products
router.get('/',  (req, res) => {
     pool.query('SELECT * FROM product', (error, results) => {
        if(error) {
            throw error;
        }
        res.json(results.rows);
    
});
});


// get single product
router.get('/:id', (req, res) => {
    const id = req.params.id;
    const sql = 'select * from product where id = $1';
    pool.query(sql,[id], (err, result)=> {
        if(err){
            console.log(err.message);
        }
        res.json(result.rows[0]);
    })
});

// post a new product
router.post('/', (req, res) => {
    res.json({
        msg: 'post /products'
    });
    res.status(404);
});

// update a product
router.put('/:id', (req, res) => {
    res.json({
        msg:'put /products/:id'
    })
});

// delete a product
router.delete('/:id', (req, res) => {
    res.json({
        msg:'delete /products/:id'
    })
});

module.exports = router ;