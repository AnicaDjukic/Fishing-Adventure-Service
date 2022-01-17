module.exports = {
    devServer: {
        proxy: process.env.NODE_ENV === 'production'
        ? 'https://fishing-adventure-website-back.herokuapp.com/'
        : 'http://localhost:8080/'
    }
}