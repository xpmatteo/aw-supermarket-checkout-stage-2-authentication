
sudo -u postgres dropdb aw_supermarket_checkout_development
sudo -u postgres dropuser aw_supermarket_checkout
sudo -u postgres dropuser $(whoami)
