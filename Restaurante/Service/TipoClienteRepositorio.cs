using Restaurante.Models;

namespace Restaurante.Service
{
    public class TipoClienteRepositorio : ITCliente
    {
        private Conexion conex = new Conexion();
        public void add(TaTipoCliente TaTipoCliente)
        {
            conex.TaTipoClientes.Add(TaTipoCliente);

            conex.SaveChanges();
        }

        public void eleminar(int id)
        {
            conex.TaTipoClientes.Remove(ObtenerPorid(id));
        }

        public IEnumerable<TaTipoCliente> GetClientesT()
        {
           return   conex.TaTipoClientes;
        }

        public void modificar(TaTipoCliente TaTipoCliente)
        {
            var obj = conex.TaTipoClientes.Where(tpro => tpro.IdTipoCliente == TaTipoCliente.IdTipoCliente)
           .FirstOrDefault();

            obj.Porcentaje = TaTipoCliente.Porcentaje;
           obj.TaClientes = TaTipoCliente.TaClientes;
            conex.SaveChanges(); ;
        }

        public TaTipoCliente ObtenerPorid(int id)
        {
            return conex.TaTipoClientes.Where(tpro => tpro.IdTipoCliente == id).FirstOrDefault();
        }
    }
}
