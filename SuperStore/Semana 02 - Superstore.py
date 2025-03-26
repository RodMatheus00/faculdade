import pandas as pd

# Define the columns to load
columns_to_use = ['Order Date', 'Ship Date', 'Ship Mode', 'Postal Code', 'Region', 
                  'Category', 'Sub-Category', 'Product Name', 'Sales', 'Quantity', 
                  'Discount', 'Profit']

# Read the CSV
df = pd.read_csv(r'C:\Users\Matheus.Rodrigues\Desktop\Me\Faculdade\Inteligência Analítica em Negócios\Semana 02 - Superstore.csv', encoding='latin1', usecols=columns_to_use)

# Function to convert a US formatted number to Brazilian style
def american_to_brazilian_format(num):
    # Format with comma as thousand separator and period as decimal
    formatted = f"{num:,.2f}"
    # Swap comma and period: use a temporary placeholder 'X'
    formatted = formatted.replace(',', 'X').replace('.', ',').replace('X', '.')
    return formatted

# Apply the conversion to the desired columns
df['Sales'] = df['Sales'].apply(american_to_brazilian_format)
df['Discount'] = df['Discount'].apply(american_to_brazilian_format)
df['Profit'] = df['Profit'].apply(american_to_brazilian_format)

print(df.head())
