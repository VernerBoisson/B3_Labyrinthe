const express = require("express");
const config = require("./config");
const methodOverride = require('method-override')
const app = express()
const database = require('./database')

const PORT = config.port;


app.use(methodOverride('_method'));
app.use(express.urlencoded({extended: true}));
app.use(express.json());

app.get('/', async (req, res) => {
    const mazes = await database.getAll();
    res.json(mazes)
});

app.get('/:id', async (req, res) => {
    const maze = await database.get(req.params.id);
    res.json(maze)
});

app.post('/', async (req, res) => {
    console.log(req.params, req.query, req.body)
    const maze = await database.insert(req.body);
    res.redirect('/')
});

app.patch('/:id', async (req, res) => {
    const maze = await database.update(req.params.id, req.body)
    res.redirect('/')
});

app.delete('/:id', async (req, res) => {
    const maze = await database.remove(req.params.id)
    res.redirect('/')
});

app.get('*', (req, res) => {
    res.json({
        code:404,
        message:"Not Found"
    })
});

app.listen(PORT);