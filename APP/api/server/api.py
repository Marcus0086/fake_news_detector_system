from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from server.Routes.routes import router as NewsRouter

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_credentials=True,
    allow_methods=['*'],
    allow_headers=['*'],
)
app.include_router(NewsRouter, prefix="/api")


@app.get("/", tags=["Root"])
async def read_root():
    return {
        "message": "Welcome to fake news detection api"
    }
