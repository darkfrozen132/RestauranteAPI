using Restaurante.Models;
using static Azure.Core.HttpHeader;

namespace Restaurante.Service
{
    public class ClienteRepositorio : ICliente
    {
        private Conexion conex = new Conexion();


        public void add(TaCliente taCliente)
        {
            conex.TaClientes.Add(taCliente);

            conex.SaveChanges();    
        }

        public void eleminar(int id)
        {
             conex.Remove(ObtenerPorid(id));
            conex.SaveChanges();    
        }

        public IEnumerable<TaCliente> GetClientes()
        {
            return conex.TaClientes;
        }

        public void modificar(TaCliente taCliente)
        {
            var obj = conex.TaClientes.Where(tpro => tpro.IdCliente == taCliente.IdCliente)
                                   .FirstOrDefault();
            obj.Nombre =  taCliente.Nombre;
            obj.IdTipoCliente = taCliente.IdTipoCliente;
            obj.IdTipoClienteNavigation = taCliente.IdTipoClienteNavigation;
            obj.TaPedidos = taCliente.TaPedidos;
            

            conex.SaveChanges();
        }

        public TaCliente ObtenerPorid(int id)
        {
            return conex.TaClientes.Where(tpro => tpro.IdCliente == id).FirstOrDefault();
        }
    }
}
