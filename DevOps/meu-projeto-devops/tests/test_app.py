# tests/test_app.py
from app import somar, subtrair

def test_somar():
    assert somar(2, 3) == 5

def test_subtrair():
    assert subtrair(5, 2) == 3
