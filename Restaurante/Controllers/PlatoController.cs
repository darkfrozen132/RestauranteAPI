using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{

    [Route("api/[Controller]")]
    [ApiController]
    public class PlatoController : Controller
    {
        public readonly IPlato _plato;

        public PlatoController(IPlato plato)
        {
            _plato = plato;
        }
        [HttpGet]
        public IActionResult Index()
        {
            return Ok(_plato.GetPlato());
        }
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var obj = _plato.ObtenerPorid(id);
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
        public IActionResult add(TaPlato obj)
        {
            _plato.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _plato.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaPlato obj)
        {
            _plato.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }

    }
}
