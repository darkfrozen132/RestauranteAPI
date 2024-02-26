using Microsoft.AspNetCore.Mvc;

using Restaurante.Service;
using Restaurante.Models;


namespace Restaurante.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class MesasController : ControllerBase
    {
        public readonly IMesas _mesas;

       public MesasController(IMesas mesas)
        {
            _mesas = mesas;
        }
        [HttpGet]
        public IActionResult Index()
        {
             return Ok(_mesas.GetMesas());
        }

        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var obj = _mesas.ObtenerPorid(id);
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
        public IActionResult add(TaMesa obj)
        {
            _mesas.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _mesas.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaMesa obj)
        {
            _mesas.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }


    }
}
