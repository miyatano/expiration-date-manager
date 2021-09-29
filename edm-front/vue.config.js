module.exports = {
    devServer: {
        port: 8888,
        disableHostCheck: true,
        proxy: {
            "/food/": {
              target: "http://localhost:8080",
            }
        }
    }
};