from flask import Flask, request, jsonify, json
from flask_cors import CORS
from dotenv import load_dotenv
import pandas as pd
import os
import operator
import requests
from datetime import datetime
import re
import json


app = Flask(__name__)
CORS(app)

load_dotenv(verbose=True)


@app.route('/getNews', methods=['GET'])
def postery():
    


if __name__ == '__main__':

    app.run(host='127.0.0.1:5000')
