using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{


    [Route("api/[Controller]")]
    [ApiController]
    public class TipoClienteController : Controller
    {

        readonly ITCliente _tCliente;

        TipoClienteController (ITCliente tCliente)
        {
            _tCliente = tCliente;   
        }
        [HttpGet]
        public IActionResult Index()
        {
            return Ok(_tCliente.GetClientesT());

        }
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var obj = _tCliente.ObtenerPorid(id);
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
        public IActionResult add(TaTipoCliente obj)
        {
            _tCliente.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _tCliente.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaTipoCliente obj)
        {
            _tCliente.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }





    }
}
