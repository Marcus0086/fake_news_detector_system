from fastapi import APIRouter, Response, status
from server.models.models import NewsSchema
import joblib
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.sequence import pad_sequences
# load model and tokenizer
model = load_model('server/models/components/fake_news_model.h5')
tokenizer = joblib.load('server/models/components/tokenizer.joblib')
router = APIRouter()


def preprocess(model, tokenizer, data):
    maxlen = 1000
    data = data.dict()
    content = data['text']
    x = [content]
    x = tokenizer.texts_to_sequences(x)
    x = pad_sequences(x, maxlen=maxlen)
    predict_val = model.predict(x)
    probability = float(predict_val[0][0])
    return {
        "message": "News seems Real" if probability >= 0.5 else "News seems fake",
        "probability": probability
    }


@router.post('/predict', status_code=200)
async def predict(data: NewsSchema, res: Response):
    try:
        response = preprocess(model, tokenizer, data)
        return {"message": response}
    except:
        res.status_code = status.HTTP_500_INTERNAL_SERVER_ERROR
        return {'message': 'error occured'}
