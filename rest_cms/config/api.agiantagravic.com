server {

        root /var/www/vue;

        # Add index.php to the list if you are using PHP
        index index.html index.htm index.nginx-debian.html;
        server_name api.agiantagravic.com; # managed by Certbot


        location / {
            proxy_pass http://127.0.0.1:8000;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #       deny all;
        #}


        listen [::]:443 ssl http2; # managed by Certbot
        listen 443 ssl http2; # managed by Certbot
        ssl_certificate /etc/letsencrypt/live/api.agiantagravic.com/fullchain.pem; # managed by Certbot
        ssl_certificate_key /etc/letsencrypt/live/api.agiantagravic.com/privkey.pem; # managed by Certbot
        include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
        ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
        ssl_session_cache shared:SSL:20m;
        ssl_session_tickets off;
}

server {
        listen 80 ;
        listen [::]:80 ;
        server_name api.agiantagravic.com;
        return 301 https://$host$request_uri; # managed by Certbot
}