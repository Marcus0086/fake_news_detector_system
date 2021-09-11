from pydantic import BaseModel


class NewsSchema(BaseModel):
    text: str

    class Config:
        schema_extra = {
            "Covid19 example": {
                "text": 'India reports 33,376 Covid-19 cases, \
                32,198 recoveries and 308 deaths in last 24 hours, as per health ministry. \
                The total cases in the country has reached 3,32,08,330, of which 3,91,516 are active cases. \
                Total recoveries in the country are 3,23,74,497 and death toll stands at 4,42,317.'
            }
        }
