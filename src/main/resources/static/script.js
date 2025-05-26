// 🔁 Variables globales para controlar el modo edición
let editMode = false;             // Indica si el formulario está en modo edición
let personaEditandoId = null;     // Guarda el ID de la persona que se está editando

// Espera a que todo el contenido del DOM esté completamente cargado antes de ejecutar la función loadPersonas
document.addEventListener('DOMContentLoaded', loadPersonas);

// Añade un "escuchador" al formulario con ID 'personaForm' para que al hacer submit se ejecute la función agregarPersona
document.getElementById('personaForm').addEventListener('submit', agregarPersona);

// Función para cargar la lista de personas desde la API y mostrarlas en la página
function loadPersonas() {
  // Hace una solicitud GET al backend para obtener la lista de personas
  fetch('/api/personas')
    .then(response => response.json()) // Convierte la respuesta en formato JSON
    .then(data => {
      const list = document.getElementById('personaList'); // Obtiene el elemento <ul> donde se mostrarán las personas
      list.innerHTML = ''; // Limpia el contenido anterior de la lista

      // Recorre cada persona recibida del backend
      data.forEach(persona => {
        const item = document.createElement('li'); // Crea un nuevo elemento <li>
        // Muestra el nombre, apellido y email de la persona
        item.textContent = `${persona.nombre} ${persona.apellido} - ${persona.email} `;

        // Botón de editar
        const editButton = document.createElement('button'); // Crea un botón
        editButton.textContent = 'Editar'; // Texto del botón
        // Al hacer clic, llama a la función cargarFormularioParaEditar pasando la persona como argumento
        editButton.onclick = () => cargarFormularioParaEditar(persona);

        // Botón de eliminar
        const deleteButton = document.createElement('button'); // Crea otro botón
        deleteButton.textContent = 'Eliminar'; // Texto del botón
        // Al hacer clic, llama a la función eliminarPersona pasando el ID de la persona
        deleteButton.onclick = () => eliminarPersona(persona.id);

        // Agrega ambos botones al elemento <li>
        item.appendChild(editButton);
        item.appendChild(deleteButton);

        // Agrega el elemento <li> completo a la lista
        list.appendChild(item);
      });
    });
}

// Función que se ejecuta al enviar el formulario para agregar una nueva persona
function agregarPersona(e) {
  e.preventDefault(); // Previene que el formulario recargue la página al hacer submit

  // Obtiene los valores ingresados en los campos del formulario
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const email = document.getElementById('email').value;

  // Crea un objeto persona con los valores capturados
  const persona = { nombre, apellido, email };

  // Si estamos en modo edición, hacemos una solicitud PUT para actualizar
  if (editMode) {
    // Envío de solicitud PUT al backend para actualizar la persona existente
    fetch(`/api/personas/${personaEditandoId}`, {
      method: 'PUT', // Método PUT para actualizar
      headers: { 'Content-Type': 'application/json' }, // Indicamos que el contenido es JSON
      body: JSON.stringify(persona) // Convertimos el objeto persona a formato JSON
    })
    .then(response => response.json()) // Convertimos la respuesta en JSON (aunque no se usa aquí)
    .then(() => {
      resetForm(); // Limpiamos el formulario y salimos del modo edición
      loadPersonas(); // Recargamos la lista para mostrar los cambios
    });

  } else {
    // Si NO estamos en modo edición, hacemos una solicitud POST para crear una nueva persona
    fetch('/api/personas', {
      method: 'POST', // Método POST para crear
      headers: { 'Content-Type': 'application/json' }, // Indicamos que el contenido es JSON
      body: JSON.stringify(persona) // Convertimos el objeto persona a JSON
    })
    .then(response => response.json()) // Convertimos la respuesta a JSON
    .then(() => {
      resetForm(); // Limpiamos el formulario
      loadPersonas(); // Recargamos la lista para incluir la nueva persona
    });
  }
}

function eliminarPersona(id) {
  // Hace una solicitud HTTP DELETE al endpoint /api/personas/{id}
  // Esto indica que queremos eliminar la persona con ese ID específico
  fetch(`/api/personas/${id}`, {
    method: 'DELETE' // Método DELETE para eliminar el recurso en el servidor
  })
  .then(() => loadPersonas()); // Cuando se complete la eliminación, vuelve a cargar la lista de personas
}

function cargarFormularioParaEditar(persona) {
  // Llena el campo de nombre con el valor actual de la persona
  document.getElementById('nombre').value = persona.nombre;

  // Llena el campo de apellido con el valor actual de la persona
  document.getElementById('apellido').value = persona.apellido;

  // Llena el campo de email con el valor actual de la persona
  document.getElementById('email').value = persona.email;

  // Guarda el ID de la persona que se está editando en una variable global
  personaEditandoId = persona.id;

  // Cambia el modo a edición (esto se usará para que el formulario haga un PUT en lugar de un POST)
  editMode = true;

  // Cambia el texto del botón del formulario para indicar que se está actualizando
  const button = document.querySelector('#personaForm button');
  button.textContent = 'Actualizar Persona';
}

function resetForm() {
  // Reinicia todos los campos del formulario (los deja vacíos)
  document.getElementById('personaForm').reset();

  // Limpia la variable global que almacenaba el ID de la persona en edición
  personaEditandoId = null;

  // Sale del modo edición y vuelve al modo de creación
  editMode = false;

  // Cambia el texto del botón del formulario para indicar que se va a agregar una nueva persona
  const button = document.querySelector('#personaForm button');
  button.textContent = 'Agregar Persona';
}
