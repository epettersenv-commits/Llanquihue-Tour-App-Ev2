![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Actividad Sumativa Semana 5 – Desarrollo Orientado a Objetos I

## 👤 Autor del proyecto
- **Nombre completo:** Elizabeth Pettersen
- **Sección:** 004A
- **Carrera:** Analista Programador Computacional
- **Sede:** Campus online

---

## 📘 Descripción general del sistema
**LlanquihueTourApp** es un sistema de consola desarrollado en Java para **Llanquihue Tour**, una agencia de turismo de la Región de Los Lagos. Gestiona a las personas vinculadas a la agencia: empleados, guías turísticos y clientes.

El programa lee los datos desde un archivo CSV, los carga en una colección (`ArrayList`), valida la información y permite listar, filtrar y buscar personas por consola. Si una línea del archivo tiene datos inválidos, se omite y se informa, sin detener la carga del resto.

Este proyecto es la continuación de la Evaluación 1. Se amplió con organización en paquetes, validaciones reutilizables, colecciones y lectura de archivos, aplicando encapsulamiento, herencia y composición.

---

## 🧱 Estructura general del proyecto

```plaintext
📁 data/
└── personas.csv                 # Datos de entrada (UTF-8, separado por ;)
📁 src/cl/agencia/turismo/
├── app/        # Main: clase principal con el menú por consola
├── model/      # Clases de dominio: Persona, Empleado, GuiaTuristico, Cliente, Direccion
├── service/    # GestorPersonas: colección, búsquedas y filtros
└── util/       # LectorCSV y Validador
```

### Clases implementadas

| Paquete | Clase | Rol |
|---|---|---|
| `model` | `Persona` | Clase base: rut, nombre, apellido, teléfono, email y `Direccion` |
| `model` | `Empleado` | Hereda de `Persona`; agrega cargo y sueldo base |
| `model` | `GuiaTuristico` | Hereda de `Persona`; agrega idioma y especialidad |
| `model` | `Cliente` | Hereda de `Persona`; agrega nacionalidad y tipo de tour |
| `model` | `Direccion` | Calle, número, comuna y región. Se usa por composición dentro de `Persona` |
| `util` | `LectorCSV` | Lee el CSV, crea los objetos y guarda los errores de las líneas inválidas |
| `util` | `Validador` | Validaciones reutilizables (correo y rut) |
| `service` | `GestorPersonas` | Guarda las personas en un `ArrayList`, evita ruts duplicados y ofrece búsquedas y filtros |
| `app` | `Main` | Carga el archivo y muestra el menú |

### Archivo de datos
`data/personas.csv` tiene 12 campos por línea, separados por `;`:

```plaintext
tipo;rut;nombre;apellido;telefono;email;calle;numero;comuna;region;dato1;dato2
```

| Tipo | dato1 | dato2 |
|---|---|---|
| `EMPLEADO` | cargo | sueldo base |
| `GUIA` | idioma | especialidad |
| `CLIENTE` | nacionalidad | tipo de tour |

Las líneas que empiezan con `#` son comentarios. El archivo incluye a propósito algunos registros inválidos (rut repetido, correo mal escrito, teléfono corto y sueldo no numérico) para mostrar la validación.

### Menú del programa
1. Listar todos
2. Filtrar por tipo (empleado, guia o cliente)
3. Filtrar por comuna
4. Buscar por nombre o apellido
5. Buscar por rut
0. Salir

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

```bash
git clone https://github.com/epettersenv-commits/Llanquihue-Tour-App-Ev2
```

2. Abre el proyecto en IntelliJ IDEA.

3. Verifica que la carpeta `data` con `personas.csv` esté en la raíz del proyecto, junto a `src`, y que `src` esté marcada como *Sources Root*.

4. Ejecuta el archivo `Main.java` desde el paquete `cl.agencia.turismo.app`. El directorio de trabajo debe ser la raíz del proyecto.

5. Usa el menú por consola para listar, filtrar y buscar personas.

---

**Repositorio GitHub:** https://github.com/epettersenv-commits/Llanquihue-Tour-App-Ev2
**Fecha de entrega:** [23/11/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Actividad Sumativa Semana 5
