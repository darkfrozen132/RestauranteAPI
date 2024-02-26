using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]

    public class PersonalController : Controller
    {

        public readonly IPersonal _personal;


        public PersonalController (IPersonal personal)
        {
            _personal = personal;   
        }
        [HttpGet]
        public IActionResult Index()
        {
            return Ok(_personal.GetPersonal()); 

        }
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var obj = _personal.ObtenerPorid(id);
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
        public IActionResult add(TaPersonal obj)
        {
            _personal.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _personal.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaPersonal obj)
        {
            _personal.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }









    }
}
