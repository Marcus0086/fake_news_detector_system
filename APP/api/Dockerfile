FROM fakenewsdetect:python
RUN pip install -r requirements.txt
COPY ./server /server
COPY ./server/models /server/models/
COPY ./server/Routes /server/Routes/
EXPOSE 8000
CMD ["python", "main.py"]