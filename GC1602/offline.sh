export V93000_MODEL=./GC1602_smart8.model
echo $V93000_MODEL

export DISPLAY_HP83000
DISPLAY_HP83000="GC1602_$(date '+%Y%m%d%H%M%S')"

/opt/hp93000/soc/prod_env/bin/HPSmarTest -o &



