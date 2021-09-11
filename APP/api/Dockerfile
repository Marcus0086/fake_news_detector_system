FROM python:slim
COPY ./server/ /app/server
COPY ./requirements.txt /app
WORKDIR /app
RUN pip install -r requirements.txt

EXPOSE 8000
CMD ["python", "main.py"]