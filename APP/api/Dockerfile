FROM python:slim
COPY ./server/ /app/server
COPY ./requirements.txt /app
WORKDIR /app
RUN pip install -r requirements.txt

EXPOSE $PORT
CMD ["gunicorn", "-w", "4", "-k", "uvicorn.workers.UvicornWorker", "server.api:app"]