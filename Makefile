.PHONY: run
run:
	mvn clean package
	docker compose up --build --wait -d
	docker logs lab4-webapp-1 -f
