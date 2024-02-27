
using global::Restaurante.Models;
using global::Restaurante.Service;
using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{


    [Route("api/[Controller]")]
    [ApiController]
    public class UsuarioController : Controller
    {

        readonly IUsuario _IUsuario;

        public UsuarioController(IUsuario IUsuario)
        {
            _IUsuario = IUsuario;
        }
        [HttpGet]
        public IActionResult Index()
        {
            return Ok(_IUsuario.GetUsuario());

        }
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var obj = _IUsuario.ObtenerPorid(id);
            if (obj == null)
            {
                var error = NotFound("El producto(" + id.ToString() + ") no existe");
                return error;
            }
            else
            {
                return Ok(obj);
            }
        }
        [HttpPost("agregar")]
        public IActionResult add(TaUsuario obj)
        {
            _IUsuario.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _IUsuario.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaUsuario obj)
        {
            _IUsuario.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }
        [HttpGet("Verificar")]
        public IActionResult verificacion(TaUsuario obj)
        {
       
            int resultadoVerificacion = _IUsuario.verfificacion(obj);

         
            switch (resultadoVerificacion)
            {
                case 0:
                   
                    return Unauthorized(); 

                case 1:
                 
                    
                    return Ok(); 
                default:
               
                  
                    return StatusCode(500); 
            }
        }


    }
}