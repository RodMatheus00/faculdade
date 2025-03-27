import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick
import folium
from geopy.geocoders import Nominatim
from geopy.extra.rate_limiter import RateLimiter
import webbrowser
import os

# --- Parte 1: LEITURA E LIMPEZA DOS DADOS ---
columns_to_use = ['Order Date', 'Ship Date', 'Ship Mode', 'Postal Code', 'Region',
                  'Category', 'Sub-Category', 'Product Name', 'Sales', 'Quantity',
                  'Discount', 'Profit']

df = pd.read_csv('Semana 02 - Superstore.csv', encoding='latin1', usecols=columns_to_use)

df['Order Date'] = pd.to_datetime(df['Order Date'], errors='coerce')
df['Sales'] = pd.to_numeric(df['Sales'], errors='coerce')
df['Profit'] = pd.to_numeric(df['Profit'], errors='coerce')
df['Discount'] = pd.to_numeric(df['Discount'], errors='coerce')

# --- Parte 2: ANÁLISE ---
sales_by_category = df.groupby('Category')['Sales'].sum().sort_values()
sales_over_time = df.groupby('Order Date')['Sales'].sum()
sales_by_region = df.groupby('Region')['Sales'].sum().sort_values()
sales_by_postal = df.groupby('Postal Code')['Sales'].sum().sort_values(ascending=False).head(10)

# --- Parte 3: GRÁFICOS COM MATPLOTLIB ---
plt.style.use('ggplot')
fig, axes = plt.subplots(2, 2, figsize=(17, 10))
fig.suptitle('📊 Sales Dashboard - Superstore', fontsize=18, fontweight='bold')

# Gráfico 1 - Vendas por Categoria
axes[0, 0].bar(sales_by_category.index, sales_by_category.values, color='#69b3a2')
axes[0, 0].set_title('Vendas por Categoria')
axes[0, 0].set_ylabel('Total de Vendas (USD)')
axes[0, 0].tick_params(axis='x', rotation=45)

# Gráfico 2 - Vendas ao Longo do Tempo
axes[0, 1].plot(sales_over_time.index, sales_over_time.values, color='#2c7fb8', linewidth=1.5)
axes[0, 1].set_title('Vendas ao Longo do Tempo')
axes[0, 1].set_ylabel('Vendas Diárias (USD)')
axes[0, 1].tick_params(axis='x', rotation=45)

# Gráfico 3 - Vendas por Região
axes[1, 0].barh(sales_by_region.index, sales_by_region.values, color='#fdae61')
axes[1, 0].set_title('Vendas por Região')
axes[1, 0].set_xlabel('Total de Vendas (USD)')
axes[1, 0].xaxis.set_major_formatter(mtick.StrMethodFormatter('${x:,.0f}'))

# Placeholder do Mapa
axes[1, 1].axis('off')
axes[1, 1].text(0, 0.6, '🌍 Clique no botão abaixo\npara abrir o mapa interativo:', 
                fontsize=12, ha='left', va='center')
axes[1, 1].text(0, 0.3, '📁 "mapa_superstore.html"', fontsize=11, ha='left', va='center', color='gray')

plt.tight_layout(rect=[0, 0, 1, 0.95])
plt.savefig("dashboard_graficos.png", dpi=300)
plt.close()

# --- Parte 4: MAPA FOLIUM ---
geolocator = Nominatim(user_agent="superstore_map")
geocode = RateLimiter(geolocator.geocode, min_delay_seconds=1)

coords = []
for code in sales_by_postal.index:
    location = geocode(f"{int(code)}, USA")
    if location:
        coords.append((code, sales_by_postal[code], location.latitude, location.longitude))

mapa = folium.Map(location=[39.5, -98.35], zoom_start=4)

for code, sales, lat, lon in coords:
    folium.CircleMarker(
        location=[lat, lon],
        radius=8,
        popup=f"ZIP: {code}<br>Sales: ${sales:,.0f}",
        color='blue',
        fill=True,
        fill_color='blue',
        fill_opacity=0.6
    ).add_to(mapa)

# Top vendedor
top = coords[0]
folium.Marker(
    location=[top[2], top[3]],
    popup=f"🏆 TOP VENDEDOR<br>ZIP: {top[0]}<br>Sales: ${top[1]:,.0f}",
    icon=folium.Icon(color='red', icon='star')
).add_to(mapa)

mapa.save("mapa_superstore.html")
print("\n✅ Mapa interativo salvo com sucesso!")

# --- Parte 5: GERAR DASHBOARD FINAL COM MAPA NO CANTO ---
html_final = f"""
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Superstore</title>
    <style>
        body {{
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }}
        .graficos {{
            width: 100%;
            text-align: center;
            margin-top: 10px;
        }}
        .graficos img {{
            max-width: 95%;
            height: auto;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
        }}
        .mapa {{
            position: fixed;
            bottom: 20px;
            right: 20px;
            width: 400px;
            height: 300px;
            border: 2px solid #333;
            box-shadow: 0 0 10px rgba(0,0,0,0.3);
            z-index: 1000;
        }}
    </style>
</head>
<body>
    <div class="graficos">
        <h2>📊 Sales Dashboard - Superstore</h2>
        <img src="dashboard_graficos.png" alt="Gráficos de Vendas">
    </div>
    <div class="mapa">
        <iframe src="mapa_superstore.html" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</body>
</html>
"""

with open("dashboard_final.html", "w", encoding="utf-8") as f:
    f.write(html_final)

print("✅ Dashboard final gerado com sucesso: dashboard_final.html")
webbrowser.open(f"file://{os.path.abspath('dashboard_final.html')}")
