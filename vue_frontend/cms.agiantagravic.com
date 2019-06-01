server {

        root /var/www/cms;

        # Add index.php to the list if you are using PHP
        index index.html index.htm index.nginx-debian.html;
        server_name cms.agiantagravic.com; # managed by Certbot

        location /

        location / {
                # First attempt to serve request as file, then
                # as directory, then fall back to displaying a 404.

                try_files $uri $uri/ /index.html;
        }

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #       deny all;
        #}


        listen [::]:443 ssl http2; # managed by Certbot
        listen 443 ssl http2; # managed by Certbot
        ssl_certificate /etc/letsencrypt/live/cms.agiantagravic.com/fullchain.pem; # managed by Certbot
        ssl_certificate_key /etc/letsencrypt/live/cms.agiantagravic.com/privkey.pem; # managed by Certbot
        include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
        ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
        ssl_session_cache shared:SSL:20m;
        ssl_session_tickets off;
}

server {
        listen 80 ;
        listen [::]:80 ;
        server_name cms.agiantagravic.com;
        gzip on;
        gzip_comp_level    7;
        gzip_min_length    256;
        gzip_vary          on;
        gzip_types
        application/atom+xml
        application/javascript
        application/json
        application/ld+json
        application/manifest+json
        application/rss+xml
        application/vnd.geo+json
        application/vnd.ms-fontobject
        application/x-font-ttf
        application/x-web-app-manifest+json
        application/xhtml+xml
        application/xml
        font/opentype
        image/bmp
        image/svg+xml
        image/x-icon
        text/cache-manifest
        text/css
        text/plain
        text/vcard
        text/vnd.rim.location.xloc
        text/vtt
        text/x-component
        text/x-cross-domain-policy;

        location ~*  \.(jpg|jpeg|png|gif|ico|css|js|pdf)$ {
                expires 7d;
        }

        return 301 https://$host$request_uri; # managed by Certbot
}