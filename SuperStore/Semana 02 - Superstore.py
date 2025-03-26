import pandas as pd
import matplotlib.pyplot as plt

# Reading the data
columns_to_use = ['Order Date', 'Ship Date', 'Ship Mode', 'Postal Code', 'Region',
                  'Category', 'Sub-Category', 'Product Name', 'Sales', 'Quantity',
                  'Discount', 'Profit']

df = pd.read_csv(r'C:\Users\Matheus.Rodrigues\Desktop\Me\Faculdade\Inteligência Analítica em Negócios\Semana 02 - Superstore.csv',
                 encoding='latin1', usecols=columns_to_use)

# Data cleaning and transformation
df['Order Date'] = pd.to_datetime(df['Order Date'], errors='coerce')
df['Sales'] = pd.to_numeric(df['Sales'], errors='coerce')
df['Profit'] = pd.to_numeric(df['Profit'], errors='coerce')
df['Discount'] = pd.to_numeric(df['Discount'], errors='coerce')

# Data aggregations
sales_by_category = df.groupby('Category')['Sales'].sum().sort_values()
sales_over_time = df.groupby('Order Date')['Sales'].sum()
sales_by_region = df.groupby('Region')['Sales'].sum().sort_values()

# Creating the dashboard (subplots)
fig, axes = plt.subplots(2, 2, figsize=(16, 10))
fig.suptitle('Sales Dashboard - Superstore', fontsize=16)

# Chart 1 - Sales by Category
axes[0, 0].bar(sales_by_category.index, sales_by_category.values, color='skyblue')
axes[0, 0].set_title('Sales by Category')
axes[0, 0].set_ylabel('Total Sales (USD)')
axes[0, 0].tick_params(axis='x', rotation=45)

# Chart 2 - Sales Over Time
axes[0, 1].plot(sales_over_time.index, sales_over_time.values, color='green')
axes[0, 1].set_title('Sales Over Time')
axes[0, 1].set_ylabel('Daily Sales (USD)')
axes[0, 1].tick_params(axis='x', rotation=45)

# Chart 3 - Sales by Region
axes[1, 0].barh(sales_by_region.index, sales_by_region.values, color='orange')
axes[1, 0].set_title('Sales by Region')
axes[1, 0].set_xlabel('Total Sales (USD)')

# Chart 4 - Pivot table (simulated as text)
pivot_table = pd.pivot_table(df, values='Sales', index='Region', columns='Category', aggfunc='sum', fill_value=0)
axes[1, 1].axis('off')  # Removes axes
table_text = pivot_table.round(2).to_string()
axes[1, 1].text(0, 1, table_text, fontsize=10, va='top', family='monospace')
axes[1, 1].set_title('Pivot Table: Region x Category', loc='left')

# Final layout adjustments
plt.tight_layout(rect=[0, 0, 1, 0.95])  # Adjust layout to avoid overlap with title
plt.show()