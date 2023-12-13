const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(createProxyMiddleware('/api', {
            target: 'http://backend1.service:7000/',
            onProxyReq: function (proxyReq, req, res) {
                proxyReq.setHeader('X-Proxy', 'Node.js');
            }
        }
    ));
}
