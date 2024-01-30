#!/usr/bin/env bash

if ! grep -q letsencrypt </etc/nginx/nginx.conf; then
  sudo certbot -n -d ssss.eu-north-1.elasticbeanstalk.com --nginx --agree-tos --email cer79cag@gmail.com
fi
